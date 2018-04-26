import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    private static final String CORREO = "Correo : %s";
    private static final String CODIGO = "Codigo : %S";

    public static void main(String[] args) throws IOException {
        metodo1("Ticket SAE");
        metodo2("francosebastian22@gmail.com", "1313");
    }

    private static void metodo1(String yourText) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(250, 30,
                BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 250, 50);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
        graphics.drawString(yourText, 10, 25);

        ImageIO.write(bufferedImage, "jpg", new File(
                "/Users/franco/Desktop/holamundo.jpg"));

        System.out.println("Image Created");
    }

    private static void metodo2(String correo, String codigo) {
           /*
           Because font metrics is based on a graphics context, we need to create
           a small, temporary image so we can ascertain the width and height
           of the final image
         */

        correo = String.format(CORREO, correo);
        codigo = String.format(CODIGO, codigo);

        BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 48);
        graphics2D.setFont(font);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int width = fontMetrics.stringWidth(correo);
        int height = 200;
        graphics2D.dispose();

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();

        //Estos son opcionales
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        graphics2D.setFont(font);

        fontMetrics = graphics2D.getFontMetrics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(correo, 0, fontMetrics.getAscent());
        graphics2D.drawString(codigo, 0, fontMetrics.getAscent()+100);
        graphics2D.dispose();
        try {
            ImageIO.write(bufferedImage, "png", new File("/Users/franco/Desktop/holamundo.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
