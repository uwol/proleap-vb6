package io.proleap.vb6.transform.java.rules.lang;

import jakarta.inject.Singleton;

import io.proleap.vb6.VisualBasic6Parser.ModuleContext;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.StandardModule;
import io.proleap.vb6.asg.metamodel.statement.enumeration.Enumeration;
import io.proleap.vb6.transform.java.util.ClassUtils;
import io.proleap.vb6.transform.rule.RuleContext;
import io.proleap.vb6.transform.rule.VbTransformRule;

@Singleton
public class ModuleRule extends VbTransformRule<ModuleContext, Module> {

	@Override
	public void apply(final ModuleContext ctx, final Module module, final RuleContext rc) {
		if (rc.getPackageName() != null && !rc.getPackageName().isEmpty()) {
			rc.p("package %s;", rc.getPackageName());
			rc.pNl();
			rc.pNl();
		}

		rc.p("import java.util.Collection;\n");
		rc.p("import java.util.Date;\n");
		rc.pNl();
		rc.p("import static io.proleap.vb6.api.App.*;\n");
		rc.p("import static io.proleap.vb6.api.CastUtils.*;\n");
		rc.p("import static io.proleap.vb6.api.Constants.*;\n");
		rc.p("import static io.proleap.vb6.api.Debug.*;\n");
		rc.p("import static io.proleap.vb6.api.Err.*;\n");
		rc.p("import static io.proleap.vb6.api.Functions.*;\n");
		rc.pNl();
		rc.p("import io.proleap.vb6.api.adodb.*;\n");
		rc.p("import io.proleap.vb6.api.com.*;\n");
		rc.p("import io.proleap.vb6.api.forms.*;\n");
		rc.p("import io.proleap.vb6.api.primitives.*;\n");
		rc.pNl();

		for (final StandardModule currentClazz : module.getProgram().getStandardModules().values()) {
			if (!currentClazz.equals(module)) {
				rc.p("import static ");

				if (rc.getPackageName() != null && !rc.getPackageName().isEmpty()) {
					rc.p("%s", rc.getPackageName());
				}

				rc.p("%s.*;", ClassUtils.getClassName(currentClazz.getName()));
				rc.pNl();
			}
		}

		for (final Module currentModule : module.getProgram().getModules()) {
			if (!currentModule.equals(module)) {
				for (final Enumeration enumeration : currentModule.getEnumerations().values()) {
					rc.p("import %s.%s.%s;", rc.getPackageName(), ClassUtils.getClassName(currentModule.getName()),
							ClassUtils.getClassName(enumeration.getName()));
					rc.pNl();
				}
			}
		}

		rc.pNl();

		final String clazzName = ClassUtils.getClassName(module.getName());

		final String superClassString;

		if (module.isCollection()) {
			superClassString = "extends VbCollection ";
		} else {
			superClassString = "";
		}

		rc.p("public class %s %s{", clazzName, superClassString);
		rc.pNl();
		rc.getPrinter().indent();

		if (ctx.moduleBody() != null) {
			rc.visit(ctx.moduleBody());
		}

		rc.getPrinter().unindent();
		rc.p("}");
		rc.pNl();

		rc.pNl();
	}

	@Override
	public Class<ModuleContext> from() {
		return ModuleContext.class;
	}
}
