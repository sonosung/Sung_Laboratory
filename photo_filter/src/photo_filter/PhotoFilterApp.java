package photo_filter;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class PhotoFilterApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Photo Filter App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 571);

        // 이미지 로딩
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("E:\\13_01_2024_3508.png")); // 이미지 경로 수정
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // 필터 적용
        BufferedImage grayscaleImage = applyGrayscaleFilter(image);
        frame.getContentPane().setLayout(null);
        JLabel label = new JLabel(new ImageIcon(grayscaleImage));
        label.setBounds(0, 0, 500, 361);
        frame.getContentPane().add(label);
        
        JSlider slider = new JSlider();
        slider.addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent evt) {
        		
        	}
        });
        slider.setBounds(92, 371, 200, 26);
        frame.getContentPane().add(slider);

        frame.setVisible(true);
    }

    private static BufferedImage applyGrayscaleFilter(BufferedImage original) {
        BufferedImage grayscale = new BufferedImage(original.getWidth(), 
                                                    original.getHeight(), 
                                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = grayscale.createGraphics();
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();

        for (int i = 0; i < grayscale.getWidth(); i++) {
            for (int j = 0; j < grayscale.getHeight(); j++) {
                int p = grayscale.getRGB(i, j);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int avg = (r + g + b) / 3;

                p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                grayscale.setRGB(i, j, p);
            }
        }
        return grayscale;
    }
}