package com.darkoverlordofdata.shmupwarz.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class DestroySystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Destroy))
    }

    override fun execute() {
        for (entity in group.entities) {
            pool.destroyEntity(entity)
        }
    }

}