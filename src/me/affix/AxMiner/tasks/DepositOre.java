/*
 * Title : AxMiner
 * Author : Keiran "Affix" Smith
 * License : GNU/GPLv3
 * Copyright : Keiran Smith
 *
 * Description : AxMiner Mines Iron Ore at Al Kharid
 */

package me.affix.AxMiner.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import me.affix.AxMiner.Task;

import java.util.concurrent.Callable;

public class DepositOre extends Task<ClientContext> {

    Area bankArea = new Area(
            new Tile(3268, 3171, 0),
            new Tile(3272, 3171, 0),
            new Tile(3273, 3166, 0),
            new Tile(3268, 3164, 0)
    );

    public DepositOre(ClientContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate(){
        return ctx.backpack.select().count()==28
                &&bankArea.contains(ctx.players.local())
                &&ctx.bank.opened()==true;

    }

    @Override
    public void execute(){

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.bank.depositInventory();
            }
        });
        ctx.backpack.select();

    }
}