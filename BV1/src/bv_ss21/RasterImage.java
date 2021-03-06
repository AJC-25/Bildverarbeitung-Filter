// BV Ue1 SS2021 Vorgabe
//
// Copyright (C) 2021 by Klaus Jung
// All rights reserved.
// Date: 2021-03-24

package bv_ss21;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class RasterImage {
	
	private static final int gray  = 0xffa0a0a0;

	public int[] argb;	// pixels represented as ARGB values in scanline order
	public int width;	// image width in pixels
	public int height;	// image height in pixels
	
	public RasterImage(int width, int height) {
		// creates an empty RasterImage of given size
		this.width = width;
		this.height = height;
		argb = new int[width * height];
		Arrays.fill(argb, gray);
	}
	
	public RasterImage(RasterImage src) {
		// copy constructor
		this.width = src.width;
		this.height = src.height;
		argb = src.argb.clone();
	}
	
	public RasterImage(File file) {
		// creates an RasterImage by reading the given file
		Image image = null;
		if(file != null && file.exists()) {
			image = new Image(file.toURI().toString());
		}
		if(image != null && image.getPixelReader() != null) {
			width = (int)image.getWidth();
			height = (int)image.getHeight();
			argb = new int[width * height];
			image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
		} else {
			// file reading failed: create an empty RasterImage
			this.width = 256;
			this.height = 256;
			argb = new int[width * height];
			Arrays.fill(argb, gray);
		}
	}
	
	public RasterImage(ImageView imageView) {
		// creates a RasterImage from that what is shown in the given ImageView
		Image image = imageView.getImage();
		width = (int)image.getWidth();
		height = (int)image.getHeight();
		argb = new int[width * height];
		image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
	}
	
	public void setToView(ImageView imageView) {
		// sets the current argb pixels to be shown in the given ImageView
		if(argb != null) {
			WritableImage wr = new WritableImage(width, height);
			PixelWriter pw = wr.getPixelWriter();
			pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
			imageView.setImage(wr);
		}
	}

	// image point operations to be added here
	
	public void convertToGray() {
		// TODO: convert the image to grayscale
		for(int pos=0; pos<argb.length; pos++) {
			int r = (argb[pos] >> 16) & 0xff;
			int g = (argb[pos] >> 8) & 0xff;
			int b = (argb[pos]) & 0xff;
			
			int value = (r+g+b)/3;
			argb[pos] = (0xff << 24) | (value << 16) | (value << 8) | value;
			}
		}
	
	/**
	 * @param quantity The fraction of pixels that need to be modified
	 * @param strength The brightness to be added or subtracted from a pixel's gray level
	 */
	//slider auf 60% -> quatity = 0.6
	public void addNoise(double quantity, int strength) {
		// TODO: add noise with the given quantity and strength
		int numPixModify = (int)( argb.length * quantity);
		//int pixStrength
		Random rand = new Random(65432);
		for(int quant=0; quant< numPixModify; quant++) {
			//zuf??llige Position "w??rfeln":
			int pos = rand.nextInt(argb.length);
			int r = (argb[pos] >> 16) & 0xff;
			int g = (argb[pos] >> 8) & 0xff;
			int b = (argb[pos]) & 0xff;	
			int value = (r+g+b)/3;
			
			//Zufallswert berechnen
			//liegt quant ??ber zufallswert -> addieren; darunter -> abziehen
			int str = rand.nextInt((argb.length)/2);
			if(quant < str) {
				value = value - strength;
			} else { value = value + strength; }
			
			//beschr??nken auf Werte zw 0 und 255:
			if(value > 255) {
				value = 255;
			} else if(value < 0) {
				value = 0;
			}
			argb[pos] = (0xff << 24) | (value << 16) | (value << 8) | value;
			}
		}
	}
	


