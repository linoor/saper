package io.saper;

import android.content.Context;
import android.widget.Button;

public class Block extends Button
{
	private boolean covered;
	private boolean disabled;
	
	public Block(Context context)
	{
		super(context);
		covered = false;
		disabled = false;
	}
	public boolean isCovered()
	{
		return covered;
	}
	public boolean isDisabled()
	{
		return disabled;
	}
}
