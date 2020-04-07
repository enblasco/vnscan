package es.verdnatura.core.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by Enrique Blasco Blanquer on 9/6/17.
 */
class VnOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "vn", null, 1) {
    companion object {
        private var instance: VnOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): VnOpenHelper {
            if (instance == null) {
                instance = VnOpenHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}

val Context.database: VnOpenHelper
    get() = VnOpenHelper.getInstance(getApplicationContext())