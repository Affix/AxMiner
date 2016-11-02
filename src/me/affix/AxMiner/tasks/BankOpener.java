/*
 * Title : AxMiner
 * Author : Keiran "Affix" Smith
 * License : GNU/GPLv3
 * Copyright : Keiran Smith
 *
 * Description : AxMiner Mines Iron Ore at Al Kharid
 */


package me.affix.AxMiner.tasks;

import me.affix.AxMiner.Task;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class BankOpener extends Task<ClientContext> {

    int bankBoothID = 76274;

    public BankOpener(ClientContext ctx){

        super(ctx);

    }

    @Override
    public boolean activate(){

        return ctx.backpack.select().count()==28
                && !ctx.objects.select().id(bankBoothID).isEmpty()
                && ctx.bank.opened()==false;

    }

    @Override
    public void execute(){
        GameObject bankBooth = ctx.objects.select().id(bankBoothID).nearest().poll();

        if(bankBooth.inViewport())
        {
            bankBooth.interact("Bank");
        } else {
            ctx.movement.step(bankBooth);
            ctx.camera.turnTo(bankBooth);
        }

    }
}