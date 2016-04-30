package com.darkoverlordofdata.shmupwarz.systems

/**
 * RemoveOffscreenShipsSystem
 *
 * Remove enemy ships when they wander off the screen
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class RemoveOffscreenShipsSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position))
    }

    override fun execute() {
        for (entity in group.entities) {
            if (entity.isEnemy) {
                if (entity.position.y < 0) {
                    pool.destroyEntity(entity)
                }
            }
        }
    }
}