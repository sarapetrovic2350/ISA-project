package ISA.BloodBank.exception;

public class ResourceConflictException extends RuntimeException {

	private static final long serialVersionUID = -4022076813761358505L;

	private String resourceId;

	public ResourceConflictException(String resourceId, String message) {
		super(message);
		this.setResourceId(resourceId);
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
