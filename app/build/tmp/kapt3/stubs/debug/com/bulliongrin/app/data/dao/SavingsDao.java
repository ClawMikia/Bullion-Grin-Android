package com.bulliongrin.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J$\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\'J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003H\'J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0018"}, d2 = {"Lcom/bulliongrin/app/data/dao/SavingsDao;", "", "getAllRecords", "Landroidx/lifecycle/LiveData;", "", "Lcom/bulliongrin/app/data/entity/SavingsRecord;", "getRecordsInDateRange", "startDate", "", "endDate", "getTotalSaved", "", "getUserStats", "Lcom/bulliongrin/app/data/entity/UserStats;", "getUserStatsSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertOrUpdateStats", "", "stats", "(Lcom/bulliongrin/app/data/entity/UserStats;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRecord", "record", "(Lcom/bulliongrin/app/data/entity/SavingsRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStats", "app_debug"})
@androidx.room.Dao()
public abstract interface SavingsDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRecord(@org.jetbrains.annotations.NotNull()
    com.bulliongrin.app.data.entity.SavingsRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM savings_records ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.bulliongrin.app.data.entity.SavingsRecord>> getAllRecords();
    
    @androidx.room.Query(value = "SELECT * FROM savings_records WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.bulliongrin.app.data.entity.SavingsRecord>> getRecordsInDateRange(long startDate, long endDate);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM savings_records")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalSaved();
    
    @androidx.room.Query(value = "SELECT * FROM user_stats WHERE id = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<com.bulliongrin.app.data.entity.UserStats> getUserStats();
    
    @androidx.room.Query(value = "SELECT * FROM user_stats WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserStatsSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.bulliongrin.app.data.entity.UserStats> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrUpdateStats(@org.jetbrains.annotations.NotNull()
    com.bulliongrin.app.data.entity.UserStats stats, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateStats(@org.jetbrains.annotations.NotNull()
    com.bulliongrin.app.data.entity.UserStats stats, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}