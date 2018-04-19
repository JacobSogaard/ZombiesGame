/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commonenemy.enemy;

/**
 * Universal, ascending enemy rating system.
 * All enemy types must be instantiated with a rating to let the game (EnemyWave)
 * know, how difficult it is to defeat.
 * @author kasper
 */
public enum Rating {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, MINIBOSS, BOSS;
}
