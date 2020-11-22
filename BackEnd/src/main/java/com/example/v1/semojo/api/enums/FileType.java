package com.example.v1.semojo.api.enums;

public enum FileType {
    Document("doc"),
    Artifact("artifact"),
    TestCase("testcase"),
    SourceCode("code"),
    Others("other")
    ;
    private String tag;

    FileType(String tag) {
        this.tag =  tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
