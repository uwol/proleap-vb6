package io.proleap.vb6.transform.java.rules.lang.statement.onerror;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.VisualBasic6Parser.BlockContext;
import io.proleap.vb6.VisualBasic6Parser.BlockStmtContext;
import io.proleap.vb6.VisualBasic6Parser.OnErrorStmtContext;
import io.proleap.vb6.VisualBasic6Parser.ResumeStmtContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;
import io.proleap.vb6.asg.metamodel.statement.resume.Resume;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class OnError_GotoLineLabel_Resume_Rule extends VbTransformRule<BlockContext, ASGElement> {

	@Override
	public void apply(final BlockContext ctx, final ASGElement asgElement, final RuleContext rc) {
		final OnError onError = findOnError(ctx, rc);
		final LineLabel errorLabel = onError.getLineLabel();

		final Resume resume = findResume(ctx, rc);
		final LineLabel resumeLabel = resume.getLineLabel();

		// --------------------------

		final List<ParserRuleContext> blockStmtsBefore = new ArrayList<ParserRuleContext>();
		final List<ParserRuleContext> blockStmtsOnError2ResumeLabel = new ArrayList<ParserRuleContext>();
		final List<ParserRuleContext> blockStmtsResumeLabel2ErrorLabel = new ArrayList<ParserRuleContext>();
		final List<ParserRuleContext> blockStmtsErrorLabel2Resume = new ArrayList<ParserRuleContext>();
		final List<ParserRuleContext> blockStmtsAfter = new ArrayList<ParserRuleContext>();

		// --------------------------

		boolean onErrorFound = false;
		boolean resumeLabelFound = false;
		boolean errorLabelFound = false;
		boolean resumeFound = false;

		for (final BlockStmtContext blockStmt : ctx.blockStmt()) {
			if (onError.getCtx().equals(blockStmt.onErrorStmt())) {
				onErrorFound = true;
			}

			if (errorLabel.getCtx().equals(blockStmt.lineLabel())) {
				errorLabelFound = true;
			}

			if (resumeLabel.getCtx().equals(blockStmt.lineLabel())) {
				resumeLabelFound = true;
			}

			if (resume.getCtx().equals(blockStmt.resumeStmt())) {
				resumeFound = true;
			}

			// --------------------------

			if (!onErrorFound) {
				blockStmtsBefore.add(blockStmt);
			} else if (!resumeLabelFound) {
				blockStmtsOnError2ResumeLabel.add(blockStmt);
			} else if (!errorLabelFound) {
				blockStmtsResumeLabel2ErrorLabel.add(blockStmt);
			} else if (!resumeFound) {
				blockStmtsErrorLabel2Resume.add(blockStmt);
			} else {
				blockStmtsAfter.add(blockStmt);
			}
		}

		// --------------------------

		for (final ParserRuleContext blockStmtBefore : blockStmtsBefore) {
			rc.visit(blockStmtBefore);
		}

		rc.p("try{");
		rc.pNl();

		for (final ParserRuleContext blockStmtOnError2ResumeLabel : blockStmtsOnError2ResumeLabel) {
			rc.visit(blockStmtOnError2ResumeLabel);
		}

		rc.p("} catch(Exception e){");
		rc.pNl();

		for (final ParserRuleContext blockStmtErrorLabel2Resume : blockStmtsErrorLabel2Resume) {
			rc.visit(blockStmtErrorLabel2Resume);
		}

		rc.p("}");
		rc.pNl();

		for (final ParserRuleContext blockStmtResumeLabel2ErrorLabel : blockStmtsResumeLabel2ErrorLabel) {
			rc.visit(blockStmtResumeLabel2ErrorLabel);
		}

		for (final ParserRuleContext blockStmtAfter : blockStmtsAfter) {
			rc.visit(blockStmtAfter);
		}
	}

	protected OnError findOnError(final BlockContext ctx, final RuleContext rc) {
		OnError result = null;

		for (final BlockStmtContext blockStmt : ctx.blockStmt()) {
			final OnErrorStmtContext onErrorCtx = blockStmt.onErrorStmt();

			if (onErrorCtx != null) {
				result = (OnError) rc.getProgram().getASGElementRegistry().getASGElement(onErrorCtx);
				break;
			}
		}

		return result;
	}

	protected Resume findResume(final BlockContext ctx, final RuleContext rc) {
		Resume result = null;

		for (final BlockStmtContext blockStmt : ctx.blockStmt()) {
			final ResumeStmtContext resumeCtx = blockStmt.resumeStmt();

			if (resumeCtx != null) {
				result = (Resume) rc.getProgram().getASGElementRegistry().getASGElement(resumeCtx);
				break;
			}
		}

		return result;
	}

	@Override
	public Class<BlockContext> from() {
		return BlockContext.class;
	}

	@Override
	public Integer getPriority() {
		return 256;
	}

	@Override
	public boolean where(final BlockContext ctx, final ASGElement asgElement, final RuleContext rc) {
		final OnError onError = findOnError(ctx, rc);
		final Resume resume = findResume(ctx, rc);

		return onError != null && resume != null && onError.getLineLabel() != null && resume.getLineLabel() != null;
	}
}
