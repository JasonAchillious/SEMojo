package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    ArtifactDao artifactDao;
    @Autowired
    DocumentDao documentDao;
    @Autowired
    SourceCodeDao sourceCodeDao;
    @Autowired
    TestCaseDao testCaseDao;
    @Autowired
    AdditionalFileDao additionalFileDao;

    public Artifact uploadArtifact(Long productId, String username){
        return null;
    }

    public AdditionalFile uploadAddition(Long productId, String username){
        return null;
    }

    public Document uploadDoc(){
        return null;
    }

    public TestCase uploadTestCase(){
        return null;
    }

    public SourceCode uploadSourceCode(){
        return null;
    }
}
