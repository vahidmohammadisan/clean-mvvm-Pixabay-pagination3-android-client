package group.payback.domain.usecase

data class ImageUseCases(
    val getRemoteImageUseCase: GetRemoteImageUseCase,
    val getLocalImageUseCase: GetLocalImageUseCase,
)