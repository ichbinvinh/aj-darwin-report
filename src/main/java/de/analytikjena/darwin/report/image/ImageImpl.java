package de.analytikjena.darwin.report.image;


/**
 * Standardimplementierung von {@link Image}.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 *
 */
public class ImageImpl implements Image {

	private StringBuilder image;
	private String imgFile;
	private String width;
	private String height;
	private String angle;
	private String scale;

	public ImageImpl(String file) {
		this.imgFile = file;
		image = new StringBuilder();
	}

	@Override
	public String getImage() {
		insert("\\includegraphics"); //$NON-NLS-1$
		insert("["); //$NON-NLS-1$
		if (width != null) {
			insert("width=" + width); //$NON-NLS-1$
		}

		if (height != null) {
			if (width != null) {
				insert(","); //$NON-NLS-1$
			}
			insert("height=" + height); //$NON-NLS-1$
		}

		if (angle != null) {
			if (width != null || height != null) {
				insert(","); //$NON-NLS-1$
			}
			insert("angle=" + angle); //$NON-NLS-1$
		}

		if (scale != null) {
			if (width != null || height != null || angle != null) {
				insert(","); //$NON-NLS-1$
			}
			insert("scale=" + scale); //$NON-NLS-1$
		}

		add("]{" + imgFile + "}"); //$NON-NLS-1$ //$NON-NLS-2$
		return image.toString();
	}

	/**
	 * Füge LaTeX-Text in die neue Zeile ein.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	private void add(String latex) {
		image.append(latex).append("\n"); //$NON-NLS-1$
	}

	/**
	 * Füge LaTeX-Text in eine Zeile ein.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	private void insert(String latex) {
		image.append(latex);
	}

	public void setImage(StringBuilder image) {
		this.image = image;
	}

	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setAngle(String angle) {
		this.angle = angle;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
}
