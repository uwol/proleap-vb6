package io.proleap.vb6.transform.java.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.proleap.vb6.asg.resolver.impl.TypeNameSanitizerImpl;
import io.proleap.vb6.asg.util.FilenameUtils;
import io.proleap.vb6.asg.util.StringUtils;

public class ClassUtils {

	public static String getClassName(final File inputFile) {
		return getClassName(FilenameUtils.removeExtension(inputFile.getName()));
	}

	public static String getClassName(final String name) {
		final String sanitizedName = new TypeNameSanitizerImpl().sanitize(name);
		final String[] classNameParts = sanitizedName.split("[.]");
		final List<String> result = new ArrayList<>();

		for (int i = 0; i < classNameParts.length; i++) {
			final String classNamePart = classNameParts[i];
			final String classNamePartLower = classNamePart.toLowerCase();
			final String classNamePartFirstUpper = StringUtils.capitalize(classNamePartLower);

			result.add(classNamePartFirstUpper);
		}

		return String.join("", result);
	}
}
