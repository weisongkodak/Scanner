package aiportal.ai.com.scanner.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aiportal.ai.com.scanner.R;


public class WaitDialog extends DialogFragment {
	protected LayoutInflater inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.view_wait_dialog, container);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity(), R.style.BaseDialog);
		setCancelable(true);
		inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.view_wait_dialog, null);

		dialog.setContentView(view);
		return dialog;
	}
}
