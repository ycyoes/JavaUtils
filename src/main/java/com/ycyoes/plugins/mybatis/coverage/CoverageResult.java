package com.ycyoes.plugins.mybatis.coverage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ycyoes.utils.ConcurrentHashSet;
import com.ycyoes.utils.MachineUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CoverageResult {
    static final String PERSIT_FILE_PATH = MachineUtil.isWindowsOS() ? "C:\\home\\mybatis\\coverage.json" : "/home/mybatis/coverage.json";
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper() {
        {
            this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            this.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        }
    };
    transient boolean fileExsits;
    ConcurrentHashSet<String> coveredIds = new ConcurrentHashSet<String>();
    float coveragePercent;
    Set<String> allMapperIds;
    Set<String> uncoveredIds;

    public CoverageResult() {
    }

    public CoverageResult(Set<String> allMapperIds) {
        this.allMapperIds = allMapperIds;
    }

    void createPersistFileIfAbsent() throws IOException {
        if (!this.fileExsits) {
            File persistFile = new File(PERSIT_FILE_PATH);
            File persistDir = persistFile.getParentFile();
            if (!persistDir.exists()) {
                persistDir.mkdirs();
            }

            if (!persistFile.exists()) {
                persistFile.createNewFile();
            }

            this.fileExsits = true;
        }
    }

    public void storeToFile() throws IOException {
        this.createPersistFileIfAbsent();
        OBJECT_MAPPER.writeValue(new File(PERSIT_FILE_PATH), this);
    }

    public void readFromFile() throws IOException {
        this.createPersistFileIfAbsent();
        CoverageResult oldResult = (CoverageResult)OBJECT_MAPPER.readValue(new File(PERSIT_FILE_PATH), CoverageResult.class);
        if (oldResult != null) {
            Set<String> coveredIds = oldResult.getCoveredIds();
            coveredIds.forEach((id) -> {
                this.addCoveredId(id);
            });
        }

    }

    public void printToConsole() {
        System.out.println("-------------mybatis coverage plugin start----------------");
        System.out.println("> AllMapperIds = " + this.allMapperIds);
        System.out.println("> Covered = " + this.coveredIds);
        System.out.println("> Uncovering = " + this.uncoveredIds);
        System.out.println("> Coverage = " + this.coveragePercent + " %");
        System.out.println("-------------mybatis coverage plugin end------------------");
    }

    public boolean addCoveredId(String id) {
        if (this.coveredIds.add(id)) {
            this.caculateCoverage();
            return true;
        } else {
            return false;
        }
    }

    public void caculateCoverage() {
        Set<String> uncoveredIds = new HashSet(this.allMapperIds);
        uncoveredIds.removeAll(this.coveredIds);
        this.uncoveredIds = uncoveredIds;
        this.coveragePercent = Float.valueOf((float)this.coveredIds.size()) / (float)this.allMapperIds.size() * 100.0F;
    }

    public Set<String> getAllMapperIds() {
        return this.allMapperIds;
    }

    public void setAllMapperIds(Set<String> allMapperIds) {
        this.allMapperIds = allMapperIds;
    }

    public Set<String> getCoveredIds() {
        return this.coveredIds;
    }

    public void setCoveredIds(ConcurrentHashSet<String> coveredIds) {
        this.coveredIds = coveredIds;
    }

    public Set<String> getUncoveredIds() {
        return this.uncoveredIds;
    }

    public void setUncoveredIds(Set<String> uncoveredIds) {
        this.uncoveredIds = uncoveredIds;
    }

    public float getCoveragePercent() {
        return this.coveragePercent;
    }

    public void setCoveragePercent(float coveragePercent) {
        this.coveragePercent = coveragePercent;
    }
}
