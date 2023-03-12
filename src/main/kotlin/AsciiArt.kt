import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

class AsciiArt(
    private val imageLocation: String,
    private val density: Int = 2,
    private val charsArrayForDraw: Array<Char> = arrayOf('@', '#', 'S', '%', '?', '*', '+', ';', ':', ',', '.')
) {
    fun generate(): String {
        val image = ImageIO.read(File(imageLocation))
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = Color(image.getRGB(x, y))
                val gray = (pixel.red + pixel.green + pixel.blue) / 3
                image.setRGB(x, y, Color(gray, gray, gray).rgb)
            }
        }
        val asciiArt = StringBuilder()
        for (y in 0 until image.height) {
            if (y % density == 0) {
                for (x in 0 until image.width) {
                    if (x % density == 0) {
                        val pixel = Color(image.getRGB(x, y))
                        val gray = (pixel.red + pixel.green + pixel.blue) / 3
                        val charIndex = gray * (charsArrayForDraw.size - 1) / 255
                        asciiArt.append(charsArrayForDraw[charIndex])
                    }
                }
                asciiArt.append("\n")
            }
        }
        return asciiArt.toString()
    }
}