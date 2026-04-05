package com.bulliongrin.app.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bulliongrin.app.data.entity.SavingsRecord;
import com.bulliongrin.app.data.entity.UserStats;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SavingsDao_Impl implements SavingsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SavingsRecord> __insertionAdapterOfSavingsRecord;

  private final EntityInsertionAdapter<UserStats> __insertionAdapterOfUserStats;

  private final EntityDeletionOrUpdateAdapter<UserStats> __updateAdapterOfUserStats;

  public SavingsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSavingsRecord = new EntityInsertionAdapter<SavingsRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `savings_records` (`id`,`amount`,`date`,`frequency`,`note`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SavingsRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        statement.bindLong(3, entity.getDate());
        if (entity.getFrequency() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFrequency());
        }
        if (entity.getNote() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNote());
        }
      }
    };
    this.__insertionAdapterOfUserStats = new EntityInsertionAdapter<UserStats>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_stats` (`id`,`xp`,`streak`,`lastSaveDate`,`totalSaved`,`achievements`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserStats entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getXp());
        statement.bindLong(3, entity.getStreak());
        statement.bindLong(4, entity.getLastSaveDate());
        statement.bindDouble(5, entity.getTotalSaved());
        if (entity.getAchievements() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAchievements());
        }
      }
    };
    this.__updateAdapterOfUserStats = new EntityDeletionOrUpdateAdapter<UserStats>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_stats` SET `id` = ?,`xp` = ?,`streak` = ?,`lastSaveDate` = ?,`totalSaved` = ?,`achievements` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserStats entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getXp());
        statement.bindLong(3, entity.getStreak());
        statement.bindLong(4, entity.getLastSaveDate());
        statement.bindDouble(5, entity.getTotalSaved());
        if (entity.getAchievements() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAchievements());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertRecord(final SavingsRecord record,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSavingsRecord.insertAndReturnId(record);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertOrUpdateStats(final UserStats stats,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserStats.insert(stats);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStats(final UserStats stats, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserStats.handle(stats);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<SavingsRecord>> getAllRecords() {
    final String _sql = "SELECT * FROM savings_records ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"savings_records"}, false, new Callable<List<SavingsRecord>>() {
      @Override
      @Nullable
      public List<SavingsRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<SavingsRecord> _result = new ArrayList<SavingsRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SavingsRecord _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpFrequency;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmpFrequency = null;
            } else {
              _tmpFrequency = _cursor.getString(_cursorIndexOfFrequency);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _item = new SavingsRecord(_tmpId,_tmpAmount,_tmpDate,_tmpFrequency,_tmpNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SavingsRecord>> getRecordsInDateRange(final long startDate,
      final long endDate) {
    final String _sql = "SELECT * FROM savings_records WHERE date >= ? AND date <= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return __db.getInvalidationTracker().createLiveData(new String[] {"savings_records"}, false, new Callable<List<SavingsRecord>>() {
      @Override
      @Nullable
      public List<SavingsRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<SavingsRecord> _result = new ArrayList<SavingsRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SavingsRecord _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpFrequency;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmpFrequency = null;
            } else {
              _tmpFrequency = _cursor.getString(_cursorIndexOfFrequency);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _item = new SavingsRecord(_tmpId,_tmpAmount,_tmpDate,_tmpFrequency,_tmpNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getTotalSaved() {
    final String _sql = "SELECT SUM(amount) FROM savings_records";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"savings_records"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<UserStats> getUserStats() {
    final String _sql = "SELECT * FROM user_stats WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"user_stats"}, false, new Callable<UserStats>() {
      @Override
      @Nullable
      public UserStats call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfXp = CursorUtil.getColumnIndexOrThrow(_cursor, "xp");
          final int _cursorIndexOfStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "streak");
          final int _cursorIndexOfLastSaveDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSaveDate");
          final int _cursorIndexOfTotalSaved = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSaved");
          final int _cursorIndexOfAchievements = CursorUtil.getColumnIndexOrThrow(_cursor, "achievements");
          final UserStats _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpXp;
            _tmpXp = _cursor.getInt(_cursorIndexOfXp);
            final int _tmpStreak;
            _tmpStreak = _cursor.getInt(_cursorIndexOfStreak);
            final long _tmpLastSaveDate;
            _tmpLastSaveDate = _cursor.getLong(_cursorIndexOfLastSaveDate);
            final double _tmpTotalSaved;
            _tmpTotalSaved = _cursor.getDouble(_cursorIndexOfTotalSaved);
            final String _tmpAchievements;
            if (_cursor.isNull(_cursorIndexOfAchievements)) {
              _tmpAchievements = null;
            } else {
              _tmpAchievements = _cursor.getString(_cursorIndexOfAchievements);
            }
            _result = new UserStats(_tmpId,_tmpXp,_tmpStreak,_tmpLastSaveDate,_tmpTotalSaved,_tmpAchievements);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUserStatsSync(final Continuation<? super UserStats> $completion) {
    final String _sql = "SELECT * FROM user_stats WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserStats>() {
      @Override
      @Nullable
      public UserStats call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfXp = CursorUtil.getColumnIndexOrThrow(_cursor, "xp");
          final int _cursorIndexOfStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "streak");
          final int _cursorIndexOfLastSaveDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSaveDate");
          final int _cursorIndexOfTotalSaved = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSaved");
          final int _cursorIndexOfAchievements = CursorUtil.getColumnIndexOrThrow(_cursor, "achievements");
          final UserStats _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpXp;
            _tmpXp = _cursor.getInt(_cursorIndexOfXp);
            final int _tmpStreak;
            _tmpStreak = _cursor.getInt(_cursorIndexOfStreak);
            final long _tmpLastSaveDate;
            _tmpLastSaveDate = _cursor.getLong(_cursorIndexOfLastSaveDate);
            final double _tmpTotalSaved;
            _tmpTotalSaved = _cursor.getDouble(_cursorIndexOfTotalSaved);
            final String _tmpAchievements;
            if (_cursor.isNull(_cursorIndexOfAchievements)) {
              _tmpAchievements = null;
            } else {
              _tmpAchievements = _cursor.getString(_cursorIndexOfAchievements);
            }
            _result = new UserStats(_tmpId,_tmpXp,_tmpStreak,_tmpLastSaveDate,_tmpTotalSaved,_tmpAchievements);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
