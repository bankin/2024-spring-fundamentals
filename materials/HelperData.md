# Helper Data

## Comments
```HTML
<div class="form-group">
    <h4>Comment</h4>
    <label>Author</label>
</div>
```

## GPX Coordinates

### Migration

```JAVA
// GPX coordinates migration from DB to file
private String getFilePath(String routeName, String username) {
	String pathPattern = "%s\\%s_%s.xml";
	return String.format(pathPattern,
			username,
			transformRouteName(routeName),	
			UUID.randomUUID());
}

private String transformRouteName(String routeName) {
	return routeName.toLowerCase()
			.replaceAll("\\s+", "_")
			.replaceAll("\"", "");
}
```

### Reading
```JAVA
// Read GPX coordinates
implementation 'io.jenetics:jpx:3.1.0'

GPX gpx = GPX.read(Path.of(basepath + route.getGpxCoordinates()));

return gpx.getTracks().get(0).getSegments().get(0).getPoints().stream()
	.map(point -> {
		List<Double> coordinates = new ArrayList<>();
		coordinates.add(point.getLongitude().doubleValue());
		coordinates.add(point.getLatitude().doubleValue());
		return coordinates;
	})
	.toList();
```

## YouTube url parser

Regex -> "http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?\u200C\u200B[\\w\\?\u200C\u200B=]*)?"

## Get powershell all environment variables
Get-ChildItem -Path Env: get all envs
