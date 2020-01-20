package com.revolut.transference.dao;

import com.revolut.transference.domain.Journal;

/**
 * Data access object for Journal entity. It handles database operations like save, find, etc.
 */
public interface IJournalDao {

    /**
     * Save Journal
     *
     * @param journal an instance of {@link Journal} to be saved
     * @return instance of the new {@link Journal} entity
     */
    Journal save(Journal journal);
}
