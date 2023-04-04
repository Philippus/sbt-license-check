package nl.gn0s1s.licensecheck

case object DisallowedLicenseException extends IllegalStateException("Disallowed licenses found")
