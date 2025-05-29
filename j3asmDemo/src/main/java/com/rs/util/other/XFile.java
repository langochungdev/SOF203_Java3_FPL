package com.rs.util.other;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class XFile {
    public static void upload(HttpServletRequest request, Part img) throws IOException {
        File saveDir = new File(request.getServletContext().getRealPath("/photo"));
        if(!saveDir.exists()) {
            saveDir.mkdirs();
        }
        String path = "/photo/" + img.getSubmittedFileName();
        String fileName = request.getServletContext().getRealPath(path);
        img.write(fileName);
    }
}
