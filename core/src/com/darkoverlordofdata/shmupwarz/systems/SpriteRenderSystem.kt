package com.darkoverlordofdata.shmupwarz.systems

/**
 * SpriteRenderSystem
 *
 * Renders all the sprites
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FillViewport
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.shmupwarz.*

class SpriteRenderSystem(game: GameScene)
      : IInitializeSystem,
        IExecuteSystem,
        ISetPool {

    val scale = .8f //2f/3f
    val game = game
    val width = game.width.toFloat()
    val height = game.height.toFloat()
    val pixelFactor = game.pixelFactor

    private lateinit var group: Group
    private lateinit var pool: Pool
    private lateinit var batch: SpriteBatch
    private lateinit var background: Sprite
    private lateinit var viewport: FillViewport
    private lateinit var camera: OrthographicCamera

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.View))

        /**
         * Set callback used by Group to sort the list of entities
         */
        group.setSort({entities: Array<Entity> ->
            entities.sortBy { e:Entity -> e.layer.ordinal }
        })
    }

    override fun initialize() {

        batch = SpriteBatch()
        camera = game.camera
        viewport = FillViewport(width/pixelFactor, height/pixelFactor, camera)
        viewport.apply()
        camera.position.set(width/(pixelFactor*2f), height/(pixelFactor*2f), 0f)
        camera.update()
        background = O2dLibrary.sprites.createSprite(O2dLibrary.getResource("background"))
    }

    override fun execute() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(background, 0f, 0f, width, height)
        for (entity in group.entities) {
            val sprite = entity.view.sprite
            if (sprite != null) {
                if (entity.hasScale)
                    sprite.setScale(entity.scale.x * scale, entity.scale.y * scale)
                else
                    sprite.setScale(scale)

                val x = sprite.width / 2f
                val y = sprite.height / 2f

                sprite.setPosition(entity.position.x - x, entity.position.y - y)
                sprite.draw(batch)
            }
        }
        batch.end()
    }

    fun resize(width: Int, height: Int) {
        viewport.update(width,height)
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0f)
    }
}