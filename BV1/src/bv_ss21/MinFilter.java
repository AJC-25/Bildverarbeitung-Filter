package bv_ss21;

import java.util.*;

public class MinFilter implements Filter {

	RasterImage sourceImage;
	RasterImage destinationImage;
	
	int width;
	int height;
	
	@Override
	public void setSourceImage(RasterImage sourceImage) {
		// TODO Auto-generated method stub
		this.sourceImage = sourceImage;
	}

	@Override
	public void setDestinationImage(RasterImage destinationImage) {
		// TODO Auto-generated method stub
		this.destinationImage = destinationImage;
	}

	@Override
	public void setKernelWidth(int kernelWidth) {
		// TODO Auto-generated method stub
		this.width = kernelWidth;
	}

	@Override
	public void setKernelHeight(int kernelHeight) {
		// TODO Auto-generated method stub
		this.height = kernelHeight;
	}

	@Override
	public void apply() {
		// TODO Auto-generated method stub
	int widthImage = sourceImage.width;
	int heightImage = sourceImage.height;
	int kxHalf = (width-1)/2;
	int kyHalf = (height-1)/2;
	
	//über pixel bild laufen:
	for(int y=0; y<heightImage-1; y++) {
		for(int x=0; x<widthImage-1; x++) {
			//int[]pix = new int[] {};	//leeres 1D-Array über Kernel
			ArrayList<Integer> pix = new ArrayList<>();
			for(int ky = (-kyHalf); ky <= kyHalf; ky++) {	//kernel y-Richtung
				for(int kx = (-kxHalf); kx <= kxHalf; kx++) {	//kernel x-Richtung
					int newY = y + ky;
					int newX = x + kx;
					
					//Randbehandlung mit Konstanter: letzten Wert wiederholen:
					if(newX < 0) {
						newX = 0;
					}
					if(newX > widthImage-1) {
						newX = widthImage-1;
					}
					if(newY < 0) {
						newY = 0;
					}
					if(newY > heightImage-1) {
						newY = heightImage-1;
					}
					
					int kPos = newY * widthImage + newX;	//position kernel			
					int kValue = sourceImage.argb[kPos];	//wert kernel		
					int a = kValue & (0xff);
					//pix = new int[a];
					pix.add(a);
				}
			}
//			applyFilter min = new applyFilter();
//			min.af();
			//int pixMin = pix[0];
			Collections.sort(pix);	//ohne sortieren vorher wird das Bild nur verschoben nach links unten
			int pixMin = pix.get(0);
			int pos = y * widthImage + x;
			destinationImage.argb[pos] = (0xff000000) | (pixMin << 16) | (pixMin << 8) | pixMin;
			}
		}
	}
}
