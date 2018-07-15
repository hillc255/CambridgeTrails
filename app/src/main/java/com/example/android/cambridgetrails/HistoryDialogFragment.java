package com.example.android.cambridgetrails;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HistoryDialogFragment extends DialogFragment {


    public HistoryDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.history_dialog,
                new LinearLayout(getActivity()), false);

        // get the key pair value from HistoryFragment
        String mKey = getArguments().getString("key");
        int mValue = Integer.parseInt(mKey);

        // set the image in the view
        ImageView imageView = view.findViewById(R.id.text_title);
        imageView.setImageResource(mValue);

        // build dialog for display
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }

}
