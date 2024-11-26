package org.example.AllCanUse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    /**
     * 覆盖写入文件内容。
     * 如果文件不存在，会自动创建文件，并且在文件名中添加时间戳确保唯一性。
     *
     * @param baseFolder      文件的基础文件夹路径
     * @param fileName        文件名
     * @param suffix          文件后缀
     * @param lines           要写入文件的内容
     * @param timemilliseconds 时间戳，用于生成唯一文件名
     */
    public void writeFile(String baseFolder, String fileName, String suffix, List<String> lines, long timemilliseconds) {
        String filePath = baseFolder + timemilliseconds + "_" + fileName + suffix;

        if (createFileIfNotExist(filePath)) {
            writeToFile(filePath, lines);
        }
    }

    /**
     * 检查文件是否存在，如果不存在则创建文件。
     *
     * @param filePath 文件路径
     * @return 如果文件存在或成功创建则返回true，否则返回false
     */
    private boolean createFileIfNotExist(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                System.out.println("文件创建失败：" + filePath);
                sleep(2000);
                return false;
            }
        }
        return true;
    }

    /**
     * 将内容写入指定的文件路径。
     *
     * @param filePath 文件路径
     * @param lines    要写入文件的内容
     */
    private void writeToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("文件写入失败：" + filePath);
            sleep(1000);
        }
    }

    /**
     * 读取文件内容。
     * 如果文件不存在，会自动创建空文件。
     *
     * @param baseFolder 文件的基础文件夹路径
     * @param fileName   文件名
     * @param suffix     文件后缀
     * @return 包含文件中每一行内容的列表
     */
    public List<String> readFile(String baseFolder, String fileName, String suffix) {
        String filePath = baseFolder + fileName + suffix;
        File file = new File(filePath);

        if (!file.exists()) {
            createFileIfNotExist(filePath); // 文件不存在时自动创建
        }

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("未找到：" + filePath);
            sleep(2000);
        }
        return lines;
    }

    /**
     * 将评论写入文件。
     * 如果文件不存在，则创建新的文件。
     *
     * @param baseFolder 文件的基础文件夹路径
     * @param fileName   文件名
     * @param suffix     文件后缀
     * @param lines      要写入文件的评论内容
     */
    public void writecommentFile(String baseFolder, String fileName, String suffix, List<String> lines) {
        String filePath = baseFolder + fileName + suffix;

        if (createFileIfNotExist(filePath)) {
            writeToFile(filePath, lines);
        } else {
            System.out.println("评论发布失败!");
            sleep(2000);
        }
    }

    /**
     * 追加内容到文件。
     * 如果文件不存在，会自动创建文件。
     *
     * @param baseFolder 文件的基础文件夹路径
     * @param fileName   文件名
     * @param suffix     文件后缀
     * @param line       要追加的内容
     */
    public void appendFile(String baseFolder, String fileName, String suffix, String line) {
        String filePath = baseFolder + fileName + suffix;

        if (createFileIfNotExist(filePath)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("追加文件失败：" + filePath + "，错误信息：" + e.getMessage());
            }
        } else {
            System.out.println("评论发布失败！");
            sleep(2000);
        }
    }

    /**
     * 使当前线程休眠指定时间。
     *
     * @param milliseconds 要休眠的毫秒数
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
