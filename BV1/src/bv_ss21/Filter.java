// BV Ue1 SS2021 Vorgabe
//
// Copyright (C) 2021 by Klaus Jung
// All rights reserved.
// Date: 2021-03-24

package bv_ss21;

public interface Filter {

	public void setSourceImage(RasterImage sourceImage);

	public void setDestinationImage(RasterImage destinationImage);

	public void setKernelWidth(int kernelWidth);

	public void setKernelHeight(int kernelHeight);

	public void apply();
	
}
