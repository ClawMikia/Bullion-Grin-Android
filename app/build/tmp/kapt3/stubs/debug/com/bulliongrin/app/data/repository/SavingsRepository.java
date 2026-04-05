package com.bulliongrin.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\fH\u0082@\u00a2\u0006\u0002\u0010\u001bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/bulliongrin/app/data/repository/SavingsRepository;", "", "dao", "Lcom/bulliongrin/app/data/dao/SavingsDao;", "(Lcom/bulliongrin/app/data/dao/SavingsDao;)V", "allRecords", "Landroidx/lifecycle/LiveData;", "", "Lcom/bulliongrin/app/data/entity/SavingsRecord;", "getAllRecords", "()Landroidx/lifecycle/LiveData;", "totalSaved", "", "getTotalSaved", "userStats", "Lcom/bulliongrin/app/data/entity/UserStats;", "getUserStats", "getRecordsInRange", "start", "", "end", "insertRecord", "", "record", "(Lcom/bulliongrin/app/data/entity/SavingsRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserStats", "amount", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SavingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.bulliongrin.app.data.dao.SavingsDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.bulliongrin.app.data.entity.SavingsRecord>> allRecords = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalSaved = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.bulliongrin.app.data.entity.UserStats> userStats = null;
    
    public SavingsRepository(@org.jetbrains.annotations.NotNull()
    com.bulliongrin.app.data.dao.SavingsDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.bulliongrin.app.data.entity.SavingsRecord>> getAllRecords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalSaved() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.bulliongrin.app.data.entity.UserStats> getUserStats() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertRecord(@org.jetbrains.annotations.NotNull()
    com.bulliongrin.app.data.entity.SavingsRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object updateUserStats(double amount, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.bulliongrin.app.data.entity.SavingsRecord>> getRecordsInRange(long start, long end) {
        return null;
    }
}