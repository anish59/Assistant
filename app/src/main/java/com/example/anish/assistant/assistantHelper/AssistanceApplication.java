package com.example.anish.assistant.assistantHelper;

import android.app.Application;

import com.example.anish.assistant.dataBaseHelper.AssistantOpenHelper;
import com.example.anish.assistant.dataBaseHelper.DatabaseManager;
import com.facebook.stetho.Stetho;

/**
 * Created by anish on 25-11-2016.
 */

public class AssistanceApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.initialize(AssistantOpenHelper.getInstance((this)));
        AssistantOpenHelper.getInstance((this)).createDataBase(this);

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

    }
}
