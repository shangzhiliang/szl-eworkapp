package com.szl.web.checkcode;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class VerificationCode extends HttpServlet {
    private static final long serialVersionUID = -11L;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        byte[] b = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            HttpSession session=request.getSession();
            String captchaId = session.getId();
            BufferedImage challenge = BfferImageCss.getInstance().getImageChallengeForID(captchaId, request.getLocale());
            session.setAttribute(captchaId+"_vccode",BfferImageCss.result);
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(baos);
            jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (CaptchaServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        b = baos.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        sos.write(b);
        sos.flush();
        sos.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
