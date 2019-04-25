using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Windows.Forms;

namespace DoctorClient
{
    class Connector
    {
        private static Connector connector;
        private TcpClient client;
        private NetworkStream networkStream;
        private Timer heartbeatTimer;
        private static IPAddress ip;
        private static int port;
        private static int packageSize;
        private static Object lockobj;
        private int heartbeatCount;
        private int currentCount;
        private byte[] buffer;
        private MessageProcessor messageProcessor ;
        private static String message = "";
        
        private Connector() {
            init();
            messageProcessor = MessageProcessor.getInstance();
        }

        public static Connector getInstance() {
            if (connector == null)
                connector = new Connector();
            return connector;
        }

        public void init(){
            
            currentCount = 0;
            heartbeatTimer = new Timer();
            heartbeatTimer.Tick += heartbeatTimer_Tick;
            heartbeatCount = Int32.Parse(ConfigReader.GetValue("heartbeatinteval"));
            packageSize = Int32.Parse(ConfigReader.GetValue("packagesize"));

            ip = IPAddress.Parse(ConfigReader.GetValue("serverip"));
            port = Int32.Parse(ConfigReader.GetValue("port"));
            client = new TcpClient();
            client.ReceiveBufferSize = 65536;
            lockobj = new Object();
         
            buffer = new byte[client.ReceiveBufferSize]; 
            connectToServer();
        }

        public void connectToServer() {
            try
            {
                client.Connect(ip, port);
                networkStream = client.GetStream();
                heartbeatTimer.Enabled = true;
                heartbeatTimer.Start();
                networkStream.BeginRead(buffer, 0, System.Convert.ToInt32(client.ReceiveBufferSize), ReceiveMessge, null);
            }
            catch (Exception ex)
            {
                throw new MyException("连接失败，请重试");
            }
        }

        private void heartbeatTimer_Tick(object sender, EventArgs e)
        {
            currentCount++;
            if (currentCount == heartbeatCount)
            {
                Send(messageProcessor.createInfo(MessageProcessor.InfoType.SEND_HEART_BAET_Enum, null));
                currentCount = 0;
            }
        }

        public void Send(string str)
        {
            Console.Write("发送加密前:" + str +"\t\t\t\t");
            str = Encryptor.AesEncrypt(str);
            str += "#";
            Console.Write("发送加密后:"+str);
            byte[] buffer1 = Encoding.UTF8.GetBytes(str);//获得缓存同时规定编码为UTF-8
            Console.WriteLine("长度:" + buffer1.Length);
            try
            {
                lock (lockobj)
                {
                    if (client.Connected)
                    {
                        for (int i = 0; i < buffer1.Length / packageSize; i++)
                        {
                            networkStream.Write(buffer1, i * packageSize, packageSize); //发往服务器
                            networkStream.Flush();
                        }
                        int size = buffer1.Length % packageSize;
                        networkStream.Write(buffer1, buffer1.Length - size, size);
                        networkStream.Flush();
                    }
                }
            }
            catch (IOException ex)
            {
                throw new MyException("离线");
            }
        }

        void ReceiveMessge(IAsyncResult ar)
        {
            try
            {
                int byteRead = networkStream.EndRead(ar);
                if (byteRead < 1)
                    return;
                else
                {
                    message += Encoding.ASCII.GetString(buffer, 0, byteRead);
                    if (message.EndsWith("#"))
                    {
                        Console.Write("接收解密前:" + message + "\t");
                        foreach (String info in message.Split('#'))
                            if (info.Length != 0) {
                                String cmd = Encryptor.AesDecrypt(info);
                                Console.WriteLine("接收解密后：" + cmd);
                                String[] messageInfo = messageProcessor.getInfo(cmd);
                                messageProcessor.analyseMessage(messageInfo);     
                            }
                        message = "";  
                    }
                    networkStream.BeginRead(buffer, 0, Convert.ToInt32(client.ReceiveBufferSize), ReceiveMessge, null);  //  zaici 接收  
                }
            }
            catch (Exception e) {
                    Console.WriteLine(e); throw new MyException("已经离线，无法读取"); }
        }
    }
}
