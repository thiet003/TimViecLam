package com.example.timviec.Model;

import java.io.Serializable;

public class Certificate implements Serializable {
    String certificate_time,certificate_detail;

    public String getCertificate_time() {
        return certificate_time;
    }

    public void setCertificate_time(String certificate_time) {
        this.certificate_time = certificate_time;
    }

    public String getCertificate_detail() {
        return certificate_detail;
    }

    public void setCertificate_detail(String certificate_detail) {
        this.certificate_detail = certificate_detail;
    }

    public Certificate(String certificate_time, String certificate_detail) {
        this.certificate_time = certificate_time;
        this.certificate_detail = certificate_detail;
    }
}
