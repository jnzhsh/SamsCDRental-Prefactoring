package com.samscdrental.failures;

import com.samscdrental.model.dto.*;

public class LateReturnDeviation
	extends Deviation
{
	private static final long serialVersionUID = -1L; 

	private OverdueRentalDTO theOverdueRentalDTO = null;
	public OverdueRentalDTO getOverdueRentalDTO()
	{
		return theOverdueRentalDTO;
	}

	public LateReturnDeviation( OverdueRentalDTO aOverdueRentalDTO )
	{
		super( "" );
		theOverdueRentalDTO = aOverdueRentalDTO;

	}

}
