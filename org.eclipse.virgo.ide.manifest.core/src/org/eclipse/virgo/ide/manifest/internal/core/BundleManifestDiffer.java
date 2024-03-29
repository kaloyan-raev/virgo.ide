/*******************************************************************************
 * Copyright (c) 2009 SpringSource, a divison of VMware, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SpringSource, a division of VMware, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.virgo.ide.manifest.internal.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.virgo.ide.manifest.core.IBundleManifestChangeListener;
import org.eclipse.virgo.ide.manifest.core.IBundleManifestChangeListener.Type;
import org.osgi.framework.Constants;
import org.springframework.util.ObjectUtils;

import org.eclipse.virgo.util.osgi.manifest.BundleManifest;
import org.eclipse.virgo.util.osgi.manifest.ExportPackage;
import org.eclipse.virgo.util.osgi.manifest.ImportBundle;
import org.eclipse.virgo.util.osgi.manifest.ImportLibrary;
import org.eclipse.virgo.util.osgi.manifest.ImportPackage;
import org.eclipse.virgo.util.osgi.manifest.RequireBundle;

/**
 * Utility that checks two {@link BundleManifest} instances for equality.
 * @author Christian Dupuis
 * @since 1.0.1
 */
class BundleManifestDiffer {

	/**
	 * Diffs the two given bundles and provides a set of changes.
	 * @param bundleManifest1 the first manifest to check
	 * @param bundleManifest2 the second manifest to check
	 * @return {@link Set} of {@link IBundleManifestChangeListener.Type} expressing the actual
	 * change.
	 */
	static Set<Type> diff(BundleManifest bundleManifest1, BundleManifest bundleManifest2) {
		if (bundleManifest1 == null && bundleManifest2 == null) {
			return Collections.emptySet();
		}
		else if ((bundleManifest1 == null && bundleManifest2 != null)
				|| (bundleManifest1 != null && bundleManifest2 == null)) {
			return BundleManifestManager.IMPORTS_CHANGED;
		}

		ImportPackage importPackageHeader1 = bundleManifest1.getImportPackage();
		ImportPackage importPackageHeader2 = bundleManifest2.getImportPackage();

		ExportPackage exportPackageHeader1 = bundleManifest1.getExportPackage();
		ExportPackage exportPackageHeader2 = bundleManifest2.getExportPackage();

		ImportLibrary importLibraryHeader1 = bundleManifest1.getImportLibrary();
		ImportLibrary importLibraryHeader2 = bundleManifest2.getImportLibrary();

		ImportBundle importBundleHeader1 = bundleManifest1.getImportBundle();
		ImportBundle importBundleHeader2 = bundleManifest2.getImportBundle();

		RequireBundle requireBundleHeader1 = bundleManifest1.getRequireBundle();
		RequireBundle requireBundleHeader2 = bundleManifest2.getRequireBundle();

		String execEnvironment1 = bundleManifest1.toDictionary().get(
				Constants.BUNDLE_REQUIREDEXECUTIONENVIRONMENT);
		String execEnvironment2 = bundleManifest2.toDictionary().get(
				Constants.BUNDLE_REQUIREDEXECUTIONENVIRONMENT);

		Set<Type> differences = new HashSet<Type>();

		if (!ObjectUtils.nullSafeEquals(importPackageHeader1, importPackageHeader2)) {
			differences.add(Type.IMPORT_PACKAGE);
		}
		if (!ObjectUtils.nullSafeEquals(execEnvironment1, execEnvironment2)) {
			differences.add(Type.IMPORT_PACKAGE);
		}
		if (!ObjectUtils.nullSafeEquals(exportPackageHeader1, exportPackageHeader2)) {
			differences.add(Type.EXPORT_PACKAGE);
		}
		if (!ObjectUtils.nullSafeEquals(importLibraryHeader1, importLibraryHeader2)) {
			differences.add(Type.IMPORT_LIBRARY);
		}
		if (!ObjectUtils.nullSafeEquals(importBundleHeader1, importBundleHeader2)) {
			differences.add(Type.IMPORT_BUNDLE);
		}
		if (!ObjectUtils.nullSafeEquals(requireBundleHeader1, requireBundleHeader2)) {
			differences.add(Type.REQUIRE_BUNDLE);
		}

		return differences;
	}

}
