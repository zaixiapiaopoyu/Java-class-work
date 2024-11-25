package org.example.AllCanUse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    /**
     * 读取文件内容。
     * 如果文件不存在，会自动创建空文件。
     *
     * @param baseFolder 文件的基础文件夹路径
     * @param fileName   文件名
     * @param suffix     文件后缀
     * @return 包含文件中每一行内容的列表
     */

    public static List<String> readFile(String baseFolder, String fileName, String suffix) {
        String filePath = baseFolder + fileName + suffix;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("未找到：" + fileName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        return lines;
    }

    /**
     * 覆盖写入文件内容。
     * 如果文件不存在，会自动创建，并在文件名中添加时间戳以确保唯一性。
     *
     * @param baseFolder      文件的基础文件夹路径
     * @param fileName        文件名
     * @param suffix          文件后缀
     * @param lines           要写入文件的内容
     * @param timemilliseconds 时间戳，用于生成唯一文件名
     */

    public static void writeFile(String baseFolder, String fileName, String suffix, List<String> lines,long timemilliseconds) {
        String filePath = baseFolder + timemilliseconds+ "_" + fileName + suffix;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                }
                else {
                    System.out.println("文章发布失败：" + fileName);
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("文章发布失败：" + fileName);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("发布文章失败：" + fileName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 将评论写入文件。
     * 如果文件不存在，则创建一个新的文件。
     *
     * @param baseFolder 文件的基础文件夹路径
     * @param fileName   文件名
     * @param suffix     文件后缀
     * @param lines      要写入文件的评论内容
     */

    public static void writecommentFile(String baseFolder, String fileName, String suffix, List<String> lines) {
        String filePath = baseFolder + fileName + suffix;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("已成功发布评论：" + fileName);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    System.out.println("评论发布失败：" + fileName);
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("评论发布失败：" + fileName);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("发布文章失败：" + fileName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 追加内容到文件。
     * 如果文件不存在，会自动创建。
     *
     * @param baseFolder 文件的基础文件夹路径
     * @param fileName   文件名
     * @param suffix     文件后缀
     * @param line       要追加的内容
     */

    public static void appendFile(String baseFolder, String fileName, String suffix, String line) {
        String filePath = baseFolder + fileName + suffix;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("评论发布成功！");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    System.out.println("评论发布失败！");
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("评论发布失败！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("追加文件失败：" + filePath + "，错误信息：" + e.getMessage());
        }
    }
}
