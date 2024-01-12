package com.anirudha.opendefaultappwithtabview;

// MessageFragment.java
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.net.Uri;

public class MessageFragment extends Fragment {

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnSendMessage).setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"));
        startActivity(intent);
    }
}
