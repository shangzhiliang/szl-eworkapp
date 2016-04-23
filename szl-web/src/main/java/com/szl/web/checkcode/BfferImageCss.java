package com.szl.web.checkcode;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class BfferImageCss {
    private static DefaultManageableImageCaptchaService instance=null;
    public static int result;
    public static ImageCaptchaService getInstance() {
        VerifImage vi= new VerifImage();
        result=vi.getResult();
        if(instance==null){
            instance = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), vi, 180, 100000, 75000);
        }else{
            instance.setCaptchaEngine(vi);
        }
        return instance;
    }
}
