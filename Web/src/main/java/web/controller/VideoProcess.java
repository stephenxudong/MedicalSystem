package web.controller;

import web.service.VideoToWord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/video")
public class VideoProcess {
    private static final Log logger = LogFactory.getLog(VideoProcess.class);
    private static final VideoToWord  converter = new VideoToWord();
    @RequestMapping(value = "/toStr",method = RequestMethod.POST)
    public
    @ResponseBody String toNatureLang(@RequestParam(value="audioData") MultipartFile video)
    {
        logger.info("start to convert video to NL");
        String str="";
        byte[] bytes=null;
        try {
            bytes = video.getBytes();
        }catch (Exception e)
        {
            System.out.println("error in get video in bytes");
        }
        synchronized (converter)
        {
            str = converter.toNL(bytes);
        }
        return str;

    }

}
