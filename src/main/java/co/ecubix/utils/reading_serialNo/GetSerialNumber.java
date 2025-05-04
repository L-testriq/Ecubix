package co.ecubix.utils.reading_serialNo;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class GetSerialNumber {
	
	private static int x; // Adjust as per the QR code location
	private static int y;
	private static int width; // Width of the QR code area
	private static int height;

	public String extractQRCodeData(int pageNumber, int position, String in_or_out) {
		String pdfPath = System.getProperty("user.dir") + "/src/test/resources/AH040320251.pdf";
		String savedImagePath = System.getProperty("user.dir") + "/downloads";
		String qrData = null;
		try {
			// Load PDF document
			PDDocument document = PDDocument.load(new File(pdfPath));
			PDFRenderer pdfRenderer = new PDFRenderer(document);

			// Check if the requested page exists
			int totalPages = document.getNumberOfPages();
			if (pageNumber < 0 || pageNumber >= totalPages) {
				System.out.println("Invalid page number! The PDF has " + totalPages + " pages.");
				document.close();
				return qrData;
			}

			// Render the specific page as an image
			BufferedImage image = pdfRenderer.renderImageWithDPI(pageNumber, 300); // 300 DPI for better QR detection
			document.close();

			// Save the image (optional)
			File outputImage = new File(savedImagePath + "/page_" + (pageNumber + 1) + ".png");
			ImageIO.write(image, "png", outputImage);

			// After taking page
			BufferedImage imageOfPdfPage = ImageIO.read(outputImage);

			if (in_or_out.equals("in")) {
				switch (position) {
				case 1:
					x = 825; 
					y = 150;
					width = 375; 
					height = 360;
					break;
				case 2:
					x = 825;
					y = 700;
					width = 375;
					height = 360;
					break;
				case 3:
					x = 825;
					y = 1250;
					width = 375;
					height = 360; 
					break;
				}

			} else if (in_or_out.equals("out")) {
				switch (position) {
				case 1:
					x = 2150;
					y = 160;
					width = 375;
					height = 360;
					break;
				case 2:
					x = 2130;
					y = 700;
					width = 375;
					height = 360;
					break;
				case 3:
					x = 2130;
					y = 1250;
					width = 375;
					height = 360; 
					break;
				}
			}

			// Crop the Barcode from the image
			BufferedImage croppedImage = imageOfPdfPage.getSubimage(x, y, width, height);

			File croppedFile = new File(savedImagePath+"/cropped_qr.png");
			ImageIO.write(croppedImage, "png", croppedFile);
			qrData =extractTextFromQR(croppedFile);
			
			if (qrData.length() >= 12) {
		        return qrData.substring(qrData.length() - 12); // Extract last 12 characters
		    }
			// Delete the saved image after extracting QR data
			if (outputImage.exists()) {
			    outputImage.delete();
			}
			if (croppedFile.exists()) {
			    croppedFile.delete();
			}

		    return qrData; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qrData;
	}
	
	public String extractTextFromQR(File file) throws IOException, NotFoundException { 
		//ZXing API Third Party plugin for QR reading in Automation
		BufferedImage bufferedImage = ImageIO.read(file);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		return result.getText();
	}

}
