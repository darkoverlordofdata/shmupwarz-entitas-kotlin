package com.darkoverlordofdata.shmupwarz.systems

/**
 * ViewManagerSystem
 *
 * Modify view appearance when components are added or removed
 *
 */
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class ViewManagerSystem()
      : ISystem,
        ISetPool {

    override fun setPool(pool: Pool) {

        /**
         * Fix up the initial sprite position
         * when a PositionComponent is added
         */
        pool.getGroup(Matcher.Position).onEntityAdded +=
        { e: GroupChangedArgs ->

            val entity = e.entity
            val sprite = entity.view.sprite
            if (sprite != null) {
                if (entity.hasPosition) {
                    val pos = entity.position
                    sprite.x = pos.x
                    sprite.y = pos.y
                }
            }
        }

        /**
         * Fix up the initial sprite color
         * when a TintComponent is added
         */
        pool.getGroup(Matcher.Tint).onEntityAdded +=
        { e: GroupChangedArgs ->

            val entity = e.entity
            val sprite = entity.view.sprite
            if (entity.hasTint) {
                val tint = entity.tint
                sprite?.setColor(tint.r, tint.g, tint.b, tint.a)
            }
        }

        /**
         * Remove the sprite color
         * when a TintComponent is removed
         */
        pool.getGroup(Matcher.Tint).onEntityRemoved +=
        { e: GroupChangedArgs ->

            val entity = e.entity
            val sprite = entity.view.sprite
            sprite?.setColor(0f, 0f, 0f, 0f)
        }

        /**
         * Fix up the initial sprite scale
         * when a ScaleComponent is added
         */
        pool.getGroup(Matcher.Scale).onEntityAdded +=
        { e: GroupChangedArgs ->

            val entity = e.entity
            val sprite = entity.view.sprite
            if (entity.hasScale) {
                val scale = entity.scale
                sprite?.setScale(scale.x, scale.y)
            }
        }
    }

}
