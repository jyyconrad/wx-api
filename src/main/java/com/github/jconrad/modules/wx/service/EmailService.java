package com.github.jconrad.modules.wx.service;

public interface EmailService {
    void sendMail(String to, String subject, String content, String... files);
}
