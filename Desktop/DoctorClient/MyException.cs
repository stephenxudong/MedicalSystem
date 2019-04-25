using System;
using System.Collections.Generic;
using System.Text;

namespace DoctorClient
{
    [Serializable]
    public class MyException : Exception
    {
        private string error;
        private Exception innerException;
        
        public MyException(){}
        
        public MyException(string msg) : base(msg){
            this.error = msg;
        }
  
        public MyException(string msg, Exception innerException) : base(msg){
            this.innerException = innerException;
            this.error = msg;
        }

        public string GetError(){
            return error;
        }
    }
}
