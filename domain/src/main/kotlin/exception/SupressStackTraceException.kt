package exception

import validation.ErrorDomain

open class SupressStackTraceException(
    errorDomains: List<ErrorDomain>,
    cause: Throwable?
) : RuntimeException(
    errorDomains[0].message,
    cause,
    true,
    false

)