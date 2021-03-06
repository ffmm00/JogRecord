package com.fm.JogRecord;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class JogView extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int CURSORLOADER_ID=0;
    private DatabaseHelper mDpHelper;
    private ListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        Button btnView=(Button)this.findViewById(R.id.btnRet);
        btnView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    finish();
            }
        });

        mAdapter=new ListAdapter(this,null,0);
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(CURSORLOADER_ID,null,this);
    }

    @Override
    public Loader<Cursor>onCreateLoader(int id,Bundle args){
        return new CursorLoader(this,JogRecordContentProvider.CONTENT_URI,null,null,null,"_id DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader,Cursor cursor){
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor>loader){
        mAdapter.swapCursor(null);
    }

}
