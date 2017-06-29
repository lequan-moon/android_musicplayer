package com.example.mypc.project_02.ulti;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuanLM on 6/29/2017.
 */

public class FileHelper {

    public static List<File> getListFilesMp3(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFilesMp3(file));
            } else {
                if (file.getName().endsWith(".mp3")) {
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }
}
