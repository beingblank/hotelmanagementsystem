package com.mg.hotelmanagementsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mg.hotelmanagementsystem.models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by moses on 7/11/18.
 */

public class HotelDatabase extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "hotel_system.db";
    public static final String TAG = HotelDatabase.class.getSimpleName();
    public static final int DATABASE_VERSION = 1;

    public HotelDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException ex) {
            Log.e(TAG, "onCreate", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (SQLException ex) {
            Log.e(TAG, "onUpgrade", ex);
        }
    }

    public void addUser(User user) {
        try {
            Dao<User, String> userDao = getDao(User.class);
            if (getUser() == null)
                userDao.create(user);
        } catch (SQLException ex) {
            Log.e(TAG, "addUser", ex);
        }
    }

    public User getUser() {
        try {
            Dao<User, String> userDao = getDao(User.class);
            List<User> users = userDao.queryForAll();
            return users.isEmpty() ? null : users.get(0);
        } catch (SQLException ex) {
            Log.e(TAG, "getUser", ex);
        }
        return null;
    }

    public void deleteUser() {
        try {
            TableUtils.clearTable(getConnectionSource(), User.class);
        } catch (SQLException ex) {
            Log.e(TAG, "deleteUser", ex);
        }
    }
}
