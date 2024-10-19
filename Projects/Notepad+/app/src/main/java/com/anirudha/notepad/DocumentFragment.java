package com.anirudha.notepad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class DocumentFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private TextInputEditText editText;
    private int position;

    // ActivityResultLauncher for opening files
    private final ActivityResultLauncher<String> openFileLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    String fileContents = readFileFromUri(uri);
                    if (fileContents != null) {
                        editText.setText(fileContents);
                        Toast.makeText(getContext(), "File opened successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to open file", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    // ActivityResultLauncher for saving files
    private final ActivityResultLauncher<Intent> saveFileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    // Get the URI from the result intent
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();
                        saveFile(uri, editText.getText().toString());
                    }
                }
            });

    public static DocumentFragment newInstance(int position) {
        DocumentFragment fragment = new DocumentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        setupToolbar(view);
        editText = view.findViewById(R.id.editText);
        registerForContextMenu(editText);
        return view;
    }

    // updated
    private void setupToolbar(View view) {
        // Find the Toolbar widget in the layout by its ID
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        // Get a reference to the AppCompatActivity (the activity containing this fragment)
        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        // Set the Toolbar as the ActionBar for the activity
        activity.setSupportActionBar(toolbar);

        // Enable the "Up" button (the left-facing arrow) in the ActionBar
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set a click listener for the navigation icon (usually the "Up" button)
        // When the icon is clicked, simulate the onBackPressed behavior
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }

// updated end

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position = getArguments().getInt(ARG_POSITION, 0);

        // Load text from a file if available
        String fileName = "document_" + position + ".txt";
        String fileContents = readFile(fileName);

        if (fileContents != null) {
            editText.setText(fileContents);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.notepad_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_open) {
            // Implement open functionality
            openFileLauncher.launch("*/*");
            return true;
        } else if (itemId == R.id.menu_save) {
            // Implement save functionality
            Intent saveIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            saveIntent.setType("text/plain");
            saveIntent.putExtra(Intent.EXTRA_TITLE, "document_" + position + ".txt");
            saveFileLauncher.launch(saveIntent);
            return true;
        } else if (itemId == R.id.menu_close) {
            // Implement close functionality
            editText.setText("");
            Toast.makeText(getContext(), "File closed", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private String readFile(String fileName) {
        try (InputStream inputStream = getContext().openFileInput(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }

            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readFileFromUri(Uri uri) {
        try (InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }

            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to read file", Toast.LENGTH_SHORT).show();
            return null;
        }
    }


    private void saveFile(Uri uri, String content) {
        try {
            // Open an OutputStream from ContentResolver
            OutputStream outputStream = requireContext().getContentResolver().openOutputStream(uri);
            if (outputStream != null) {
                // Write content to OutputStream
                outputStream.write(content.getBytes());
                outputStream.close();
                Toast.makeText(getContext(), "File saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to open OutputStream", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to save file", Toast.LENGTH_SHORT).show();
        }
    }

}
