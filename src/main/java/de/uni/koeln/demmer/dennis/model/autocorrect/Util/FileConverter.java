package de.uni.koeln.demmer.dennis.model.autocorrect.Util;

import de.uni.koeln.demmer.dennis.controller.DictionaryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileConverter {


    public static File convertToFile(MultipartFile file) {
        File convFile = null;
        try {
            convFile = new File("data/tmp/" + file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convFile;
    }

    public static File convertTozip(List<File> files) {
        try {
            FileOutputStream fos = new FileOutputStream("data/tmp/result.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (File file : files) {
                addToZipFile(file, zos);
            }
            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("data/tmp/result.zip");
    }

    private static void addToZipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {

        Logger logger = LoggerFactory.getLogger(FileConverter.class);

        logger.info("Writing '" + file.getName() + "' to zip file");

        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }


    public static List<File> unpackZIP(File fileZip) throws IOException {

        List<File> unpacked = new ArrayList<>();
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            String fileName = zipEntry.getName();
            File newFile = new File("data/tmp/" + fileName);
            unpacked.add(newFile);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();


        return unpacked;
    }

}
