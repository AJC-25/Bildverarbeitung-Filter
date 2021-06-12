// BV Ue1 SS2021 Vorgabe
//
// Copyright (C) 2021 by Klaus Jung
// All rights reserved.
// Date: 2021-03-24

package bv_ss21;

public class DummyFilter implements Filter {

	@Override
	public void setSourceImage(RasterImage sourceImage) {
	}

	@Override
	public void setDestinationImage(RasterImage destinationImage) {
	}

	@Override
	public void setKernelWidth(int kernelWidth) {
		//int i = kernelWidth;
	}

	@Override
	public void setKernelHeight(int kernelHeight) {
	}

	@Override
	public void apply() {
	// this is a dummy filter that does nothing
	}

}
