package com.fhtiger.plugins.humptransfer;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Code2HumpAction
 *
 * @author LFH
 * @since 2019年08月15日 17:27
 */
public final class Code2UnderLineAction extends Code2HumpOrUnderLine {

	@Override
	public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
		transfer(anActionEvent,false);
	}

}
