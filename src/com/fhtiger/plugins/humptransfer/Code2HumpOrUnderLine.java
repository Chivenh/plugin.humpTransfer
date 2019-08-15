package com.fhtiger.plugins.humptransfer;

import com.intellij.diff.actions.ProxyUndoRedoAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.components.JBComponent;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Code2HumpWithReverse
 *
 * @author LFH
 * @since 2019年08月15日 16:42
 */
public abstract class Code2HumpOrUnderLine extends AnAction {

	/**
	 * 将选中内容转成驼峰形式
	 * @param anActionEvent -
	 */
	@Override abstract public void actionPerformed(@NotNull AnActionEvent anActionEvent);

	/**
	 * 选中文本后才可以使用
	 * @param anActionEvent 状态变化控制
	 */
	@Override
	public void update(@NotNull AnActionEvent anActionEvent) {
		boolean b=false;
		final Editor mEditor = anActionEvent.getData(PlatformDataKeys.EDITOR);
		if (null != mEditor) {
			SelectionModel model = mEditor.getSelectionModel();
			final String selectedText = model.getSelectedText();
			b=!TextUtils.isEmpty(selectedText);
		}
		anActionEvent.getPresentation().setEnabledAndVisible(b);
	}

	/**
	 * @param anActionEvent 事件
	 * @param toHump 是否转驼峰,非则转下划线
	 * @param smallCaml    转驼峰时smallCaml参数
	 * @param uppercase   转下划线时upperCase参数
	 */
	private void transfer(@NotNull AnActionEvent anActionEvent,boolean toHump,boolean smallCaml,boolean uppercase){
		final Editor mEditor = anActionEvent.getData(PlatformDataKeys.EDITOR);
		if (null == mEditor) {
			return;
		}
		SelectionModel model = mEditor.getSelectionModel();
		final String selectedText = model.getSelectedText();
		if (TextUtils.isEmpty(selectedText)) {
			return;
		}

		final String resultText = toHump?  HumpTransferUtil.trasfer2hump(selectedText,smallCaml): HumpTransferUtil.transfer2underline(selectedText,uppercase);

		// Work off of the primary caret to get the selection info
		Caret primaryCaret = mEditor.getCaretModel().getPrimaryCaret();
		int start = primaryCaret.getSelectionStart();
		int end = primaryCaret.getSelectionEnd();
		final Document document = mEditor.getDocument();
		Project theProject = anActionEvent.getProject();
		// Replace the selection with a fixed string.
		// Must do this document change in a write action context.
		WriteCommandAction.runWriteCommandAction(theProject, () ->
				document.replaceString(start, end, resultText)
		);

		// De-select the text range that was just replaced
//		primaryCaret.removeSelection();

		showPopupBalloon(mEditor,selectedText);
	}

	protected void transfer2hump(@NotNull AnActionEvent anActionEvent,boolean smallCaml){
		transfer(anActionEvent,true,smallCaml,false);
	}

	protected void transfer2hump(@NotNull AnActionEvent anActionEvent){
		transfer2hump(anActionEvent,true);
	}

	protected void transfer2underline(@NotNull AnActionEvent anActionEvent,boolean uppercase){
		transfer(anActionEvent,false,true,uppercase);
	}

	protected void transfer2underline(@NotNull AnActionEvent anActionEvent){
		transfer2underline(anActionEvent,false);
	}

	/**
	 * 将转换结果在小窗口显示出来
	 * @param editor 编辑器
	 * @param result 结果
	 */
	private void showPopupBalloon(final Editor editor, final String result) {
		ApplicationManager.getApplication().invokeLater(() -> {
			JBPopupFactory factory = JBPopupFactory.getInstance();
			factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
					.setFadeoutTime(5000)
					.createBalloon()
					.show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
		});
	}
}
