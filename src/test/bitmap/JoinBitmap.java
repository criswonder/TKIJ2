
package test.bitmap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JoinBitmap {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            mergeImage(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mergeImage(File parentFile) throws IOException {

        int rows = 8;
        int cols = 8;

        int chunkWidth, chunkHeight;
        int type;
        
        // 读入小图
        File[][] imgFiles = new File[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                imgFiles[i][j] = new File("C:\\Users\\DGD\\Desktop\\361\\3\\" + i + "_" + j + ".png");
            }
        }

        // 创建BufferedImage
        BufferedImage[][] buffImages = new BufferedImage[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buffImages[i][j] = ImageIO.read(imgFiles[i][j]);
            }
        }
        type = buffImages[0][0].getType();
        chunkWidth = buffImages[0][0].getWidth();
        chunkHeight = buffImages[0][0].getHeight();

        // 设置拼接后图的大小和类型
        BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);

        // 写入图像内容
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                finalImg.createGraphics().drawImage(buffImages[i][j], chunkWidth * i, chunkHeight *j, null);
            }
        }

        // 输出拼接后的图像
        File wholeFile = new File("C:\\img\\merge\\finalImg1.jpg");
        if(!wholeFile.exists()) {
            wholeFile.getParentFile().mkdirs();
            wholeFile.createNewFile();
        }
        ImageIO.write(finalImg, "jpeg",wholeFile );

        System.out.println("完成拼接！");
    }

}
